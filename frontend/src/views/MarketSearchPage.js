import Header from '../components/common/Header';
import Navbar from '../components/common/Navbar';
import KakaoMap from '../components/common/KakaoMap';
import MapExample from '../components/common/MapExample';


const MarketSearchPage = () => {


  return (
    <div className="App flex flex-col text-3xl h-full w-full bg-background-fill">
      <Header/>
      <div className='flex flex-col flex-grow'>
        <input/>
        {/* <KakaoMap/> */}
        <MapExample/>
      </div>
      <Navbar/>
    </div>
  );
}

export default MarketSearchPage;
