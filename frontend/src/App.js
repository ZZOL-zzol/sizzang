import './App.css';
import MainPage from './views/MainPage';
import SellerMyPage from './views/mypage/seller/SellerMyPage';
import StoreInfoPage from './views/mypage/seller/StoreInfoPage';
import AccountChangePage from './views/mypage/AccountChangePage';
import ConsumerMyPage from './views/mypage/consumer/ConsumerMyPage';


const App = () => {
  return (
    <div className="App min-h-screen flex w-screen">
      {/* <MainPage/> */}

      <ConsumerMyPage/>
    </div>
  );
}

export default App;
