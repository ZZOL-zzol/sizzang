import Header from "../components/common/Header";
import DetailInfoCard from "../components/common/DetailInfoCard";
import Tabs from "../components/common/Tabs";
import Navbar from "../components/common/Navbar";
import { useLocation, useNavigate } from "react-router-dom";
import MarketStoreCard from "../components/common/MarketStoreCard";
import ProductCard from "../components/store/ProductCard";
import HistoryCard from "../components/history/HistoryCard";
import ReviewCard from "../components/store/ReviewCard";
import { API_URL } from "../lib/constants";
import { useEffect, useState } from "react";
import axios from "axios";

const reviewList = [
  {
    reCode: 1,
    userName: "차차아버님",
    reContent: "배가 터질거같아요 책임지세요 윽",
    reDate: "2023.09.10",
    reImg: "../chacha2.jpg",
    reScore: 9,
  },
  {
    reCode: 1,
    userName: "차차아버님",
    reContent: "배가 터질거같아요 책임지세요 윽",
    reDate: "2023.09.10",
    reImg: "../chacha2.jpg",
    reScore: 8,
  },
];

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


const StoreDetailPage = () => {
  const [store, setStore] = useState({});
  const [productList, setProductList] = useState([]);
  const location = useLocation();
  const navigate = useNavigate();
  const stCode = location.pathname.split("/")[2];
  const [currentTab, setCurrentTab] = useState(0);

  useEffect(() => {
    axios
      .get(`${API_URL}/store/${stCode}`)
      .then((res) => {
        setStore(res.data.data);
      })
      .catch((err) => console.log(err));

    axios
      //.get(`${API_URL}/store/${stCode}`)
      .get(`http://localhost:8080/product/1`)
      .then((res) => {
        setProductList(res.data.data);
      })
      .catch((err) => console.log(err));

    }, []);


  return (
    <div className="flex flex-col w-full h-full bg-white outline outline-1">
      <Header title="점포 상세" backButton basketButton />
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
          <Tabs type="store" tab1='상품 목록' tab2='리뷰' onTabClick={setCurrentTab} />
        </div>
      </div>
      {currentTab === 0 ? (
        <div className="flex flex-col w-full h-[350px] overflow-auto">
          {productList.map((product) => (
            <ProductCard product={product} store={store} />
          ))}
        </div>
      ) : (
        <div className="flex flex-col w-full h-[350px] overflow-auto gap-2 bg-background-fill">
          {reviewList.map((review) => (
            <ReviewCard review={review} />
          ))}
        </div>
      )}

      <Navbar />
    </div>
  );
};
export default StoreDetailPage;
