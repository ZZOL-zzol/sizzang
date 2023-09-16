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

const StoreDetailPage = () => {
  const [store, setStore] = useState({});
  const [productList, setProductList] = useState([]);
  const [reviewList, setReviewList] = useState([]);
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
      .get(`${API_URL}/product/${stCode}`)
      .then((res) => {
        setProductList(res.data.data);
      })
      .catch((err) => console.log(err));

      axios
      .post(
        `${API_URL}/review/get/store`,
        JSON.stringify({stCode : stCode,}),
        {
          headers: { "Content-Type": "application/json" },
        }
      )
      .then((res) => {
        setReviewList(res.data.data);
      })
      .catch((err) => console.log(err));

    }, []);

  return (
    <div className="flex flex-col w-full h-full bg-white">
      <Header title="점포 상세" backButton basketButton route={`/market/${store.mkCode}`} />
      <div className="relative w-full items-center py-16">
        <div className="absolute top-[264px] w-full">
          <DetailInfoCard store={store} storeReCnt={reviewList.length}/>
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
        <div className="w-full h-8">
          <Tabs type="store" tab1='상품 목록' tab2='리뷰' onTabClick={setCurrentTab} />
        </div>
        {currentTab === 0 ? (
        <div className="flex flex-col max-h-[412px] overflow-auto">
          {productList.map((product) => (
            <ProductCard key={product.pdCode} product={product} store={store} />
          ))}
        </div>
      ) : (
        <div className="flex flex-col w-full h-full overflow-auto gap-2 bg-background-fill">
          {reviewList.map((review) => (
            <ReviewCard key={review.reCode} review={review} isStore={1} />
          ))}
        </div>
      )}
      </div>
      <Navbar />
    </div>
  );
};
export default StoreDetailPage;
