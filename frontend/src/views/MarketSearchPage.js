import Header from '../components/common/Header';
import Navbar from '../components/common/Navbar';
import KakaoMap from '../components/common/KakaoMap';
import MapExample from '../components/common/MapExample';


const MarketSearchPage = () => {


  return (
    <div className="App flex flex-col text-3xl h-full w-full bg-background-fill">
      <Header title='시장 찾기' button={true}/>
      <div className='flex flex-col'>
        <MapExample/>
      </div>
      <Navbar/>
    </div>
  );
}

export default MarketSearchPage;
