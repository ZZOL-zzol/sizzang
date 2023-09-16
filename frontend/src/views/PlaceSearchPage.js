import Header from "../components/common/Header";
import Navbar from "../components/common/Navbar";
import KakaoMap from "../components/common/KakaoMap";
import MapExample from "../components/common/MapExample";
import MarketStoreCard from "../components/common/MarketStoreCard";
import MapPractice from "../components/common/MapPractice";
import { API_URL } from "../lib/constants";
import { useEffect, useState } from "react";
import axios from "axios";
import Loading from "../components/common/Loading";

//처음 맵이랑 시장목록 나오는 화면.
//인피니티스크롤 어떻게 적용함..?
//검색기능적용해야함

const PlaceSearchPage = () => {
  const [mkLatitude, setMkLatitude] = useState(37.2249262);
  const [mkLongtitude, setMkLongtitude] = useState(127.11655);
  const [mkName, setMkName] = useState("신한은행 블루캠퍼스");
  const [marketList, setMarketList] = useState([]);
  const [selectedMarket, setSelectedMarket] = useState({});
  const [keyword, setKeyword] = useState("");
  const [searched, setSearched] = useState(false);
  const [offset, setOffset] = useState(0);

  const onSearchButtonClick = () => {
    axios
      .post(
        `${API_URL}/market/search`,
        JSON.stringify({ mkName: keyword, limit: 20, offset: 0 }),
        { headers: { "Content-Type": "application/json" } }
      )
      .then((res) => setMarketList(res.data.data))
      .catch((err) => console.log(err));
  };

  const onMarketCardClick = (value) => {
    setMkLatitude(value.mkLatitude);
    setMkLongtitude(value.mkLongtitude);
    setMkName(value.mkName);
  };
  useEffect(() => {
    axios
      .post(
        `${API_URL}/market/getAll`,
        JSON.stringify({ limit: 20, offset: 0 }),
        {
          headers: { "Content-Type": "application/json" },
        }
      )
      .then((res) => {
        console.log(res.data.data);
        setMarketList(res.data.data);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="App flex flex-col text-3xl h-full w-full bg-background-fill py-16">
      <Header title="시장 찾기" button={true} />
      <div className="flex flex-col">
        {/* <MapExample/> */}
        <MapPractice
          marketList={marketList}
          keyword={keyword}
          setKeyword={setKeyword}
          onSearchButtonClick={onSearchButtonClick}
          mkLatitude={mkLatitude}
          mkLongtitude={mkLongtitude}
          mkName={mkName}
        />
      </div>
      <div className="h-full overflow-auto">
        {searched
          ? marketList.map((market) =>
              market.mkName.includes(keyword) ? (
                <MarketStoreCard
                  key={market.mkCode}
                  market={market}
                  setSelectedMarket={() => onMarketCardClick(market)}
                />
              ) : null
            )
          : marketList.map((market) => (
              <MarketStoreCard
                key={market.mkCode}
                market={market}
                setSelectedMarket={() => onMarketCardClick(market)}
              />
            ))}
      </div>
      <Navbar />
    </div>
  );
};

export default PlaceSearchPage;
