import './App.css';
import MainPage from './views/MainPage';
import SellerMyPage from './views/mypage/seller/SellerMyPage';

const App = () => {
  return (
    <div className="App min-h-screen flex w-screen">
      {/* <MainPage/> */}
      <SellerMyPage/>
    </div>
  );
}

export default App;
