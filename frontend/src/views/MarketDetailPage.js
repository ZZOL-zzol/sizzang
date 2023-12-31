import Header from "../components/common/Header";
import DetailInfoCard from "../components/common/DetailInfoCard";
import Tabs from "../components/common/Tabs";
import Navbar from "../components/common/Navbar";
import { useLocation, useNavigate } from "react-router-dom";
import MarketStoreCard from "../components/common/MarketStoreCard";
import ReviewCard from "../components/store/ReviewCard";
import { API_URL } from "../lib/constants";
import { useEffect, useState } from "react";
import axios from "axios";

//시장상세
//점포목록 가져오기
//  - 점포 카테고리 버튼으로 만들던가 하기.
//이중 스크롤 문제

const MarketDetailPage = () => {
  const [marketInfo, setMarketInfo] = useState({});
  const [storeList, setStoreList] = useState([]);
  const [reviewList, setReviewList] = useState([]);
  const [currentTab, setCurrentTab] = useState(0);
  const location = useLocation();
  const navigate = useNavigate();

  const mkCode = location.pathname.split("/")[2];

  useEffect(() => {
    axios
      .get(`${API_URL}/market/info/${mkCode}`)
      .then((res) => {
        setMarketInfo(res.data.data);
      })
      .catch((err) => console.log(err));

    axios
      .get(`${API_URL}/store/market/${mkCode}`)
      .then((res) => {
        setStoreList(res.data.data);
      })
      .catch((err) => console.log(err));

    axios
      .post(
        `${API_URL}/review/get/market`,
        JSON.stringify({ mkCode: mkCode }),
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
      <Header title="시장 상세" backButton route="/place" />
      <div className="relative w-full h-full items-center py-16">
        <div className="absolute top-[264px] w-full">
          <DetailInfoCard market={marketInfo} marketReCnt={reviewList.length} />
        </div>
        <div className="w-full h-[260px]">
          {marketInfo.mkImg ? (
            <img
              src={marketInfo.mkImg ? marketInfo.mkImg : ""}
              alt="배경사진"
              className="w-full h-[260px]"
            />
          ) : null}
        </div>

        <div className="mt-20 mb-2">
          <div>{marketInfo.mkAddress}</div>
          <div>{marketInfo.mkPhone}</div>
        </div>

        <div className="w-full h-8">
          <Tabs
            type="store"
            tab1="점포 목록"
            tab2="전체 리뷰"
            onTabClick={setCurrentTab}
          />
        </div>
        {currentTab === 0 ? (
          <div className="flex flex-col max-h-[412px] overflow-scroll">
            {storeList.map((store) => (
              <MarketStoreCard key={store.stCode} store={store} />
            ))}
          </div>
        ) : (
          <div className="flex flex-col w-full h-full overflow-auto gap-2 bg-background-fill">
            {reviewList.map((review) => (
              <ReviewCard key={review.reCode} review={review} />
            ))}
          </div>
        )}
      </div>

      <Navbar />
    </div>
  );
};
export default MarketDetailPage;
