import Header from '../components/common/Header';
import Navbar from '../components/common/Navbar';
import KakaoMap from '../components/common/KakaoMap';
import MapExample from '../components/common/MapExample';
import MarketStoreCard from '../components/common/MarketStoreCard';
import { API_URL } from "../lib/constants";
import { useEffect, useState } from "react";
import axios from "axios";


// const MarketListExample = [{
//   mkCode: 1,
//   regionCode: 1,
//   mkName: "중앙시장",
//   mkAddress: "관악구 봉천동 466",
//   mkArea: "자치구... 관악구?",
//   mkImg: "../chacha2.jpg",
//   mkToilet: "1",
//   mkParking: "1",
//   mkPhone: "010-6664-9510",
//   mkLatitude: "",
//   mklongtitude: "",
// },{
//   mkCode: 1,
//   regionCode: 1,
//   mkName: "중앙시장",
//   mkAddress: "관악구 봉천동 466",
//   mkArea: "자치구... 관악구?",
//   mkImg: "../chacha2.jpg",
//   mkToilet: "1",
//   mkParking: "1",
//   mkPhone: "010-6664-9510",
//   mkLatitude: "",
//   mklongtitude: "",
// },{
//   mkCode: 1,
//   regionCode: 1,
//   mkName: "중앙시장",
//   mkAddress: "관악구 봉천동 466",
//   mkArea: "자치구... 관악구?",
//   mkImg: "../chacha2.jpg",
//   mkToilet: "1",
//   mkParking: "1",
//   mkPhone: "010-6664-9510",
//   mkLatitude: "",
//   mklongtitude: "",
// },];


const PlaceSearchPage = () => {

  const [marketList, setMarketList] = useState([]);

  useEffect(() => {
    axios
      // .get(`${API_URL}/market/getAll`)
      .post(`http://localhost:8080/market/getAll`,
      JSON.stringify({limit : 10, offset:0,}),         {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        console.log(res.data.data);
        setMarketList(res.data.data);
      })
      .catch((err) => console.log(err));

    }, []);



  return (
    <div className="App flex flex-col text-3xl h-full w-full bg-background-fill">
      <Header title='장소 찾기' button={true}/>
      <div className='flex flex-col'>
        <MapExample/>
      </div>
      <div>
        {marketList.map(market => <MarketStoreCard market={market}/>)}
        </div> 
      <Navbar/>
    </div>
  );
}

export default PlaceSearchPage;
