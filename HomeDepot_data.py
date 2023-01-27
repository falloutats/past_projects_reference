import scrapy
from ..Utils import *
import json
import os


class DataSpider(scrapy.Spider):
    name = 'Data'

    print("Enter Name for Output file:")
    filenm = input()
    cmp = data_dict
    count = 0
    def closed(self, reason):
        if not os.path.exists("Output"):
            os.mkdir("Output")
        cmp = []
        for _, value in self.cmp.items():
            for item in value:
                cmp.append(item)
        df = pd.DataFrame(cmp)
        df.to_excel(f"Output/{self.filenm}.xlsx", index=False)

    def start_requests(self):
        for item_id in prod_ids:
            for zip_code in zip_codes:
                yield scrapy.Request(
                    url=STORE_URL.format(zip_code),
                    headers=headers_1,
                    meta={"item_id": item_id, "zip_code": zip_code},
                    dont_filter=True,
                    callback=self.parse
                )
    
    def parse(self, response):
        item_id = response.meta["item_id"]
        zip_code = response.meta["zip_code"]
        json_resp = json.loads(response.body)
        store_id = json_resp.get("stores")[store_index].get("storeId")
        yield scrapy.Request(
            url=API_URL,
            method="POST",
            headers=headers,
            body=json.dumps(payload(item_id, zip_code, store_id)),
            meta={"prod_code": item_id, "zip_code": zip_code},
            dont_filter=True,
            callback=self.parse_detail
        )

    def parse_detail(self, response):
        prod_code = response.meta["prod_code"]
        zip_code = response.meta["zip_code"]
        json_resp = json.loads(response.body)
        product = json_resp.get("data").get("product")
        prod_name = product.get("identifiers").get("productLabel")
        pricing = product.get("pricing")
        original_price = pricing.get("original")
        discounted_price = pricing.get("value")
        if not original_price:
            original_price = f"${discounted_price}"
            discounted_price = ""
        else:
            original_price = f"${original_price}"
            discounted_price = f"${discounted_price}" if discounted_price else ""
        self.cmp.get(prod_code).append({
            "Product Name": prod_name,
            "Original Price": original_price,
            "Discounted Price": discounted_price,
            "Zip Code": zip_code
        })
        self.count += 1
        self.logger.info(f"Scraped ---------------> {self.count}")