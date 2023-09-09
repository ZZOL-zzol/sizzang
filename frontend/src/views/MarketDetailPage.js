import Header from "../components/common/Header";
import DetailInfoCard from "../components/common/DetailInfoCard";
import Tabs from "../components/common/Tabs";
import Navbar from "../components/common/Navbar";
import { useLocation, useNavigate } from "react-router-dom";
import MarketStoreCard from "../components/common/MarketStoreCard";

const MarketExample = {
  mkCode: 1,
  regionCode: 1,
  mkName: "중앙시장",
  mkAddress: "관악구 봉천동 466",
  mkArea: "자치구... 관악구?",
  mkImg: "../chacha2.jpg",
  mkToilet: "1",
  mkParking: "1",
  mkPhone: "010-6664-9510",
  mkLatitude: "",
  mklongtitude: "",
};


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

const MarketDetailPage = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const mkCode = location.pathname.split("/")[2];
  console.log(mkCode);

  

  return (
    <div className="flex flex-col w-full h-full bg-white outline outline-1">
      <Header title='시장 상세' backButton/>
      <div className="relative w-full overflow-auto items-center">
        <div className="absolute top-[200px] w-full">
          <DetailInfoCard Market={MarketExample} />
        </div>
        <div className="w-full h-[260px]">
          <img
            src={MarketExample.mkImg ? MarketExample.mkImg : "../Logo.png"}
            alt="배경사진"
            className="w-full h-[260px]"
          />
        </div>

        {/* 내 소개 */}
        <div className="mt-20 mb-2">
          <div>{MarketExample.mkAddress}</div>
          <div>{MarketExample.mkPhone}</div>
        </div>

        {/* <div className={scrollY !==undefined && scrollY > 314? 'sticky top-[56px]': 'w-full'}> */}
        <div className="w-full">
          <Tabs type='market'/>
        </div>
      </div>
      <div className="flex flex-col w-full h-[350px] overflow-auto">
        {storeList.map(store => <MarketStoreCard store={store}/>)}
      
      </div>
      <Navbar />
    </div>
  );
};
export default MarketDetailPage;
