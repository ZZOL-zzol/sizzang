import MarketStoreCard from '../components/common/MarketStoreCard';
import Carousel from '../components/common/Carousel';
import Header from '../components/common/Header';
import Navbar from '../components/common/Navbar';
import TextInput from '../components/common/TextInput';
import DetailInfoCard from '../components/common/DetailInfoCard';
import SmallButton from '../components/common/SmallButton';
import SearchBar from '../components/common/SearchBar';
import Category from '../components/product/Category';

const ProductSearchPage = () => {
  return (
    <div className="App flex flex-col text-3xl h-full w-full bg-background-fill">
      <Header title='상품' button={true}/>
      <div className='flex flex-col flex-grow'>
        <div className='bg-white w-full h-16'>
        <SearchBar placeholder='상품을 검색하세요.'/>
        </div>
        <Category></Category>
      <Carousel></Carousel>
      <MarketStoreCard/>
      </div>
      <Navbar/>
    </div>
  );
}

export default ProductSearchPage;
