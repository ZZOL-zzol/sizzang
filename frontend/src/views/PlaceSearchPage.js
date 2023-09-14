import Header from '../components/common/Header';
import Navbar from '../components/common/Navbar';
import KakaoMap from '../components/common/KakaoMap';
import MapExample from '../components/common/MapExample';
import MarketStoreCard from '../components/common/MarketStoreCard';
import MapPractice from '../components/common/MapPractice';


const MarketListExample = [{
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
},{
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
},{
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
},];


const PlaceSearchPage = () => {


  return (
    
    <div className="App flex flex-col text-3xl h-full w-full bg-background-fill">
      <Header title='장소 찾기' button={true}/>
      <div className='flex flex-col'>
        {/* <MapExample/> */}
        <MapPractice/>
      </div>
      <div>
        {MarketListExample.map(market => <MarketStoreCard market={market}/>)}
        </div> 
      <Navbar/>
    </div>
  );
}

export default PlaceSearchPage;
