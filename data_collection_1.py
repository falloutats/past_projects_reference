import scrapy
from ..Utils import *
import json


class BigStuffSpider(scrapy.Spider):
    name = 'big_stuff'

    def start_requests(self):
        for i, item in enumerate(addresses):
            yield scrapy.Request(
                url=GOOGLE_API.format(quote_plus(item)),
                headers=headers,
                meta={"i": i, "address": item},
                dont_filter=True,
                callback=self.parse
            )

    def parse(self, response):
        address = response.meta["address"]
        json_resp = json.loads(response.body)
        data = json_resp.get("results")[0]
        lat = data.get("geometry").get("location").get("lat")
        lng = data.get("geometry").get("location").get("lng")
        yield scrapy.Request(
            url=SET_LOCATION_URL,
            method="POST",
            headers=headers_1,
            body=json.dumps(payload(lat, lng, address)),
            meta={"cookiejar": response.meta["i"], "address": address},
            dont_filter=True,
            callback=self.set_location
        )

    def set_location(self, response):
        address = response.meta["address"]
        yield scrapy.Request(
            url=URL,
            headers=headers,
            meta={"cookiejar": response.meta["cookiejar"], "address": address},
            dont_filter=True,
            callback=self.parse_listings
        )

    def parse_listings(self, response):
        address = response.meta["address"]
        main = response.xpath('//section//div/a')

        for stuff in main:
            name = stuff.xpath(".//h2/text()").get()
            links = stuff.xpath(".//@href").get()
            if name is not None:
                #if len(name) < 34:
                yield scrapy.Request(
                    url=links,
                    dont_filter=True,
                    callback=self.parse_internal_info,
                    meta={"cookiejar": response.meta["cookiejar"], 'Type': name, "address": address}
                )

    def parse_internal_info(self, response):
        address = response.meta["address"]
        type_head = response.request.meta['Type']
        main_page = response.xpath('(//div[contains(@class, "small-12 columns")])[2]//a')

        for info in main_page:
            item_name = info.xpath('.//h2/text()').get()
            item_cost_day = info.xpath('(//div[contains(@class, "pricing-display")]//div//div/text())[1]').get()
            item_cost_week = info.xpath('(//div[contains(@class, "pricing-display")]//div//div/text())[2]').get()
            item_cost_month = info.xpath('(//div[contains(@class, "pricing-display")]//div//div/text())[3]').get()

            if item_name is not None:
                yield {
                    "address": address,
                    'item_heading': type_head,
                    'sub_heading_item': item_name,
                    'by_day': item_cost_day,
                    'by_week': item_cost_week,
                    'by_month': item_cost_month,
                }