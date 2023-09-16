import MarketStoreCard from "../common/MarketStoreCard";
import { useEffect, useState } from "react";
import axios from "axios";
import { API_URL} from "../../lib/constants";

// const storeList = [
//   {
//     stCode: 1,
//     mkCode: 1,
//     stOwner: "차차아버님",
//     stName: "네네치킨",
//     stPhone: "010-6664-9510",
//     stImg: "../chacha2.jpg",
//     stAccount: "",
//     stAccountHolder: "정재웅",
//     stIntro: "파닭은 네네가 제일 맛있는듯",
//     stTime: "",
//     stAddress : '관악구 봉천로 466',
//     scName : '음식점'
//   },
//   {
//     stCode: 1,
//     mkCode: 1,
//     stOwner: "차차아버님",
//     stName: "네네치킨",
//     stPhone: "010-6664-9510",
//     stImg: "../chacha2.jpg",
//     stAccount: "",
//     stAccountHolder: "정재웅",
//     stIntro: "파닭은 네네가 제일 맛있는듯",
//     stTime: "",
//     stAddress : '관악구 봉천로 466',
//     scName : '음식점'
//   },
//   {
//     stCode: 1,
//     mkCode: 1,
//     stOwner: "차차아버님",
//     stName: "네네치킨",
//     stPhone: "010-6664-9510",
//     stImg: "../chacha2.jpg",
//     stAccount: "",
//     stAccountHolder: "정재웅",
//     stIntro: "파닭은 네네가 제일 맛있는듯",
//     stTime: "",
//     stAddress : '관악구 봉천로 466',
//     scName : '음식점'
//   },
// ];

const ProductAverageCard = (props) => {
  const [priceNow, setPriceNow] = useState(0);
  const [priceMonthAgo, setPriceMonthAgo] = useState(0);
  const [storeList, setStoreList] = useState([]);
  useEffect(() => {
    axios
    .get(`http://localhost:8080/store/prtag/${props.product.tagCode}`)
    .then((res) => {
      setStoreList(res.data.data);
    })
    .catch((err) => console.log(err));
    setPriceNow(Number(props.product.tagCost));
  }, []);

  return (
    <div className="collapse bg-white rounded-none w-full">
      <input type="checkbox" />
      <div className="collapse-title p-0">
        <div
          className="card card-side bg-base-100 rounded-none border-b-2"
          onClick={props.setSelectedPdCode}
        >
          <div className="card-body p-3 justify-between">
            <div className="gap-0 flex justify-between">
              <div className="card-title text-base">
                {props.product.tagName}
                <span className="text-xs text-left">
                  ({props.product.tagUnit})
                </span>
              </div>
              <div className="flex items-center">
                <span className="text-base text-left mr-2">평균 {props.product.tagCost}원</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="collapse-content">
        {storeList.map((store, index) => (
          <MarketStoreCard store={store} key={index} />
        ))}
      </div>
    </div>
  );
};

export default ProductAverageCard;
