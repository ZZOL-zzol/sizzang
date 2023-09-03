import './App.css';
import MainPage from './views/MainPage';
import SellerMyPage from './views/mypage/seller/SellerMyPage';
import StoreInfoPage from './views/mypage/seller/StoreInfoPage';

const App = () => {
  return (
    <div className="App min-h-screen flex w-screen">
      {/* <MainPage/> */}
      <StoreInfoPage/>
    </div>
  );
}

export default App;
