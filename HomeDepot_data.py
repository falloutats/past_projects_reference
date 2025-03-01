import scrapy
import json
from itemloaders import ItemLoader
from ..items import HomeDepotItem as hd_item
from ..Utils import (
    headers_1,
    headers,
    payload,
    API_URL,
    STORE_URL,
    prod_ids,
    location_context)


class DataSpider(scrapy.Spider):
    """
    A Scrapy Spider for crawling
     and extracting product listings data.

    Attributes:
        name (str): Name of the spider.
    """

    name = "listings"

    def start_requests(self):
        """
        This method generates the initial requests
         to fetch store details based on product IDs and zip codes.

        Yields:
            scrapy.Request: A Request object for Scrapy to process.
        """

        for item_id in prod_ids:

            for zip_code, store_number in location_context.items():

                yield scrapy.Request(
                    url=STORE_URL.format(zip_code),
                    headers=headers_1,
                    meta={"item_id": item_id,
                        "zip_code": zip_code,
                        "store_number": store_number,},
                    callback=self.get_store,
                    dont_filter=True)

    def get_store(self, response):
        """Get store ID and request product data"""

        item_id = response.meta["item_id"]
        zip_code = response.meta["zip_code"]
        store_number = response.meta["store_number"]
        json_resp = json.loads(response.body)

        # Try user store_number first, fallback to first available store
        store_id = store_number

        if not store_id and json_resp.get("stores"):

            store_id = json_resp["stores"][0].get("store_id")

        if not store_id:

            raise ValueError("No store ID found")

        return scrapy.Request(
            url=API_URL,
            method="POST",
            headers=headers,
            body=json.dumps(payload(item_id, zip_code, store_id)),
            meta={"prod_code": item_id, "zip_code": zip_code},
            dont_filter=True,
            callback=self.parse_data,
        )

    def parse_data(self, response):

        loader = ItemLoader(item=hd_item(), response=response)

        loader.add_value("zip_code", response.meta["zip_code"])

        json_resp = json.loads(response.body)

        product = json_resp.get("data", {}).get("product")

        if not product:
            return

        identifiers = product.get("identifiers", {})
        product_label = identifiers.get("productLabel", "")
        item_id = product.get("itemId", "")

        loader.add_value("product_name", f"{product_label}+{item_id}")

        pricing = product.get("pricing", {})

        try:
            bulk_price = pricing.get("alternate", {}).get("bulk", {}).get("value", 0)
            loader.add_value("bulk_pricing", bulk_price)

        except AttributeError:
            loader.add_value("bulk_pricing", 0)

        original_price = pricing.get("original")
        discounted_price = pricing.get("value")

        if not original_price:
            loader.add_value("original_price", discounted_price)
            loader.add_value("discounted_price", 0)

        else:
            loader.add_value("original_price", original_price)
            loader.add_value("discounted_price", discounted_price or 0)

        yield loader.load_item()
