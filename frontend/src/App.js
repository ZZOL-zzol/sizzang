import './App.css';
import MainPage from './views/MainPage';
import SellerMyPage from './views/mypage/seller/SellerMyPage';
import StoreInfoPage from './views/mypage/seller/StoreInfoPage';
import AccountChangePage from './views/mypage/AccountChangePage';

const App = () => {
  return (
    <div className="App min-h-screen flex w-screen">
      <MainPage/>

      {/* <AccountChangePage/> */}
    </div>
  );
}

export default App;
