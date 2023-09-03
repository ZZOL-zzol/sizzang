import MarketStoreCard from '../components/common/MarketStoreCard';
import Carousel from '../components/common/Carousel';
import Header from '../components/common/Header';
import Navbar from '../components/common/Navbar';
import TextInput from '../components/common/TextInput';
import DetailInfoCard from '../components/common/DetailInfoCard';

const MainPage = () => {
  return (
    <div className="App flex flex-col text-3xl h-full w-full bg-gray-100">
      <Header/>
      <div className='flex flex-col flex-grow'>바디이이이이
      <Carousel></Carousel>
      <TextInput/>
      <MarketStoreCard/>
      <DetailInfoCard/>
      </div>
      <Navbar/>
    </div>
  );
}

export default MainPage;
