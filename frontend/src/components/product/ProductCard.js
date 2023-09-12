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
    stAddress : '관악구 봉천로 466',
    scName : '음식점'
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
    stAddress : '관악구 봉천로 466',
    scName : '음식점'
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
    stAddress : '관악구 봉천로 466',
    scName : '음식점'
  },
];

const ProductCard = (props) => {


  return (
    <div className="collapse bg-white rounded-none">
      <input type="checkbox" />
      <div className="collapse-title p-0 pt-2 ">
        <div
          className="card card-side bg-base-100 rounded-none border-b-2"
          onClick={props.setSelectedPdCode}
        >
          <div className="card-body p-3 justify-between">
            <div className="gap-0 flex justify-between">
              <div className="card-title text-base">{props.product.pdName}</div>
              <span className="text-sm text-left">{props.product.pdCost}</span>
            </div>

            <div className="text-right text-sm text-myerror">▲1000</div>
          </div>
        </div>
      </div>
      <div className="collapse-content">{storeList.map((store,index)=><MarketStoreCard store={store} key={index}/>)}</div>
    </div>
  );
};

export default ProductCard;
