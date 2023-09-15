import Header from '../components/common/Header';
import Navbar from '../components/common/Navbar';
import KakaoMap from '../components/common/KakaoMap';
import MapExample from '../components/common/MapExample';
import MarketStoreCard from '../components/common/MarketStoreCard'; 
import MapPractice from '../components/common/MapPractice'; 
import { API_URL } from "../lib/constants";
import { useEffect, useState } from "react";
import axios from "axios"; 
 

//처음 맵이랑 시장목록 나오는 화면.
//인피니티스크롤 어떻게 적용함..?
//검색기능적용해야함

const PlaceSearchPage = () => {

  const [marketList, setMarketList] = useState([]);
  const [offset, setOffset] = useState(0);

  useEffect(() => {
    axios
      .post(`${API_URL}/market/getAll`,
      JSON.stringify({limit : 10, offset:0,}),
        {
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
        {/* <MapExample/> */}
        <MapPractice/>
      </div>
      <div>
        {marketList.map(market => <MarketStoreCard key={market.mkCode} market={market}/>)}
        </div> 
      <Navbar/>
    </div>
  );
}

export default PlaceSearchPage;

// 스크롤..
// const PlaceSearchPage = () => {
//   const [marketList, setMarketList] = useState([]);
//   const [offset, setOffset] = useState(0);
//   const [loading, setLoading] = useState(false);

//   useEffect(() => {
//     window.addEventListener('scroll', handleScroll);
//     return () => {
//       window.removeEventListener('scroll', handleScroll);
//     };
//   }, []);

//   useEffect(() => {
//     if (offset >= 0) {
//       setLoading(true);
//       // axios.post(`${API_URL}/market/getAll`,
//       axios.post(`http://localhost:8080/market/getAll`,
//         JSON.stringify({ limit: 10, offset: offset }),
//         {
//           headers: { "Content-Type": "application/json" },
//         })
//         .then((res) => {
//           const newData = res.data.data;
//           setMarketList([...marketList, ...newData]);
//           setLoading(false);
//         })
//         .catch((err) => {
//           console.log(err);
//           setLoading(false);
//         });
//     }
//   }, [offset]);

//   const handleScroll = () => {
//     if (
//       window.innerHeight + document.documentElement.scrollTop ===
//       document.documentElement.offsetHeight
//     ) {
// setOffset(offset + 1);
//     }
//   };

//   return (
//     <div className="App flex flex-col text-3xl h-full w-full bg-background-fill">
//       <Header title='장소 찾기' button={true}/>
//       <div className='flex flex-col'>
//         <MapExample/>
//       </div>
//       <div>
//         {marketList.map(market => <MarketStoreCard key={market.mkCode} market={market}/>)}
//       </div>
//       {loading && <p>Loading...</p>}
//       <Navbar/>
//     </div>
//   );
// }

// export default PlaceSearchPage;

