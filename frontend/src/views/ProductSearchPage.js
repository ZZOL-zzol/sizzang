import MarketStoreCard from '../components/common/MarketStoreCard';
import Carousel from '../components/common/Carousel';
import Header from '../components/common/Header';
import Navbar from '../components/common/Navbar';
import TextInput from '../components/common/TextInput';
import DetailInfoCard from '../components/common/DetailInfoCard';
import SmallButton from '../components/common/SmallButton';

const ProductSearchPage = () => {
  return (
    <div className="App flex flex-col text-3xl h-full w-full bg-background-fill">
      <Header/>
      <div className='flex flex-col flex-grow'>바디이이이이
      <Carousel></Carousel>
      <TextInput/>
      <MarketStoreCard/>
      <DetailInfoCard/>
      <SmallButton/>
      </div>
      <Navbar/>
    </div>
  );
}

export default ProductSearchPage;
