import Header from "../components/common/Header";
import DetailInfoCard from "../components/common/DetailInfoCard";
import Tabs from "../components/common/Tabs";
import Navbar from "../components/common/Navbar";
import { useLocation, useNavigate } from "react-router-dom";
import MarketStoreCard from "../components/common/MarketStoreCard";
import ProductCard from '../components/store/ProductCard'


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
  
  
  const store =
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
      scName : '음식점',
      stScore : 9
    };


    const productList = [
        { pdCode:1, pcCode:1, stCode:'', mkCode:'', scCode:'', pdName: "옛날통닭", pdCost: 50000, pdIntro : '파닭파닭' },
        { pdCode:1, pcCode:1, stCode:'', mkCode:'', scCode:'', pdName: "옛날통닭", pdCost: 50000, pdIntro : '배고프닭' },
        { pdCode:1, pcCode:1, stCode:'', mkCode:'', scCode:'', pdName: "옛날통닭", pdCost: 50000, pdIntro : '너무배고프닭' },
        { pdCode:1, pcCode:1, stCode:'', mkCode:'', scCode:'', pdName: "옛날통닭", pdCost: 50000 },
      ];
  
  const StoreDetailPage = () => {
    const location = useLocation();
    const navigate = useNavigate();
  
    const stCode = location.pathname.split("/")[2];
    console.log(stCode);
  
    
  
    return (
      <div className="flex flex-col w-full h-full bg-white outline outline-1">
        <Header title='점포 상세' backButton basketButton/>
        <div className="relative w-full overflow-auto items-center">
          <div className="absolute top-[200px] w-full">
            <DetailInfoCard store={store} />
          </div>
          <div className="w-full h-[260px]">
            <img
              src={store.stImg ? store.stImg : "../Logo.png"}
              alt="배경사진"
              className="w-full h-[260px]"
            />
          </div>
  
          {/* 내 소개 */}
          <div className="mt-20 mb-2">
            <div>{store.stAddress}</div>
            <div>{store.stPhone}</div>
          </div>
  
          {/* <div className={scrollY !==undefined && scrollY > 314? 'sticky top-[56px]': 'w-full'}> */}
          <div className="w-full">
            <Tabs type='market'/>
          </div>
        </div>
        <div className="flex flex-col w-full h-[350px] overflow-auto">
          {productList.map(product => <ProductCard product={product} store={store}/>)}
        
        </div>
        <Navbar />
      </div>
    );
  };
  export default StoreDetailPage;