import { useEffect, useState } from "react";
import MarketStoreCard from "../common/MarketStoreCard";

const storeList = [
  {
    stCode: 1,
    mkCode: 1,
    stOwner: "차차아버님",
    stName: "네네치킨",
    stPhone: "010-6664-9510",
    stImg: "../chacha2.jpg",
    stAccount: "",
    stAccountHolder: "정재웅",
    stIntro: "파닭은 네네가 제일 맛있는듯",
    stTime: "",
    stAddress: "관악구 봉천로 466",
    scName: "음식점",
  },
  {
    stCode: 1,
    mkCode: 1,
    stOwner: "차차아버님",
    stName: "네네치킨",
    stPhone: "010-6664-9510",
    stImg: "../chacha2.jpg",
    stAccount: "",
    stAccountHolder: "정재웅",
    stIntro: "파닭은 네네가 제일 맛있는듯",
    stTime: "",
    stAddress: "관악구 봉천로 466",
    scName: "음식점",
  },
  {
    stCode: 1,
    mkCode: 1,
    stOwner: "차차아버님",
    stName: "네네치킨",
    stPhone: "010-6664-9510",
    stImg: "../chacha2.jpg",
    stAccount: "",
    stAccountHolder: "정재웅",
    stIntro: "파닭은 네네가 제일 맛있는듯",
    stTime: "",
    stAddress: "관악구 봉천로 466",
    scName: "음식점",
  },
];

const ProductCurrentPriceCard = (props) => {
  const [priceNow, setPriceNow] = useState(0);
  const [priceMonthAgo, setPriceMonthAgo] = useState(0);

  useEffect(() => {
    // console.log("프롭스");
    // console.log(props);
    // if (props.product.dpr1.length > 3) {
    //   setPriceNow(
    //     Number(
    //       props.product.dpr1
    //         .split(",")[0]
    //         .concat(props.product.dpr1.split(",")[1])
    //     )
    //   );
    // } else {
    //   setPriceNow(Number(props.product.dpr1));
    // }
    // if (props.product.dpr3.length > 3) {
    //   setPriceMonthAgo(
    //     Number(
    //       props.product.dpr3
    //         .split(",")[0]
    //         .concat(props.product.dpr3.split(",")[1])
    //     )
    //   );
    // } else {
    //   setPriceMonthAgo(Number(props.product.dpr3));
    // }
  }, []);

  return (
    <div className=" bg-white rounded-none w-full">
      <div className="p-0">
        <div
          className="card card-side bg-base-100 rounded-none border-b-2"
        >
          <div className="card-body p-3 justify-between">
            <div className="gap-0 flex justify-between">
              <div className="card-title text-base">
                {props.product.productName}
                <span className="text-xs text-left">
                  ({props.product.unit})
                </span>
              </div>
              <div className="flex items-center">
                <span className="text-base text-left mr-2">{props.product.dpr1}원</span>
                {priceNow - priceMonthAgo > 0 ? (
                  <span className="text-xs text-left text-myerror">
                    ▲{priceNow - priceMonthAgo}
                  </span>
                ) : (
                  <span className="text-xs text-left">
                    ▼{priceMonthAgo - priceNow}
                  </span>
                )}
              </div>
            </div>

            <div className="text-right text-sm text-outline">
              한달전 {props.product.dpr3}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductCurrentPriceCard;
