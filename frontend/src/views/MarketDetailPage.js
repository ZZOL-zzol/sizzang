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


const StoreExample = [{},{},{},{}]

const MarketDetailPage = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const mkCode = location.pathname.split("/")[2];
  console.log(mkCode);

  const navigateTo = () => {
    navigate('/market/'+mkCode)
  }

  return (
    <div className="flex flex-col w-full h-full bg-white outline outline-1">
      <Header/>
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
        {StoreExample.map(store => <MarketStoreCard store={store}/>)}
      
      </div>
      <Navbar />
    </div>
  );
};
export default MarketDetailPage;
