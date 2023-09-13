import "./App.css";
import { Navigate, Route, Routes } from "react-router-dom";
import EntrancePage from "./views/EntrancePage";
import SignupPage from "./views/SignupPage";
import MainPage from "./views/MainPage";
import ProfilePage from "./views/ProfilePage";
import ProductSearchPage from "./views/ProductSearchPage";
import MarketDetailPage from "./views/MarketDetailPage";
import PlaceSearchPage from "./views/PlaceSearchPage";
import StoreDetailPage from "./views/StoreDetailPage";
import BasketPage from "./views/BasketPage";
import HistoryPage from "./views/HistoryPage";
import ReviewUploadPage from "./views/ReviewUploadPage";
import StampPage from "./views/StampPage";

const isLogedin = true;

const App = () => {

  return (
    <div className="App h-screen flex w-screen">
      <Routes>
        <Route path="/" element={<Navigate to="/entrance" />}></Route>
        <Route path="/entrance" element={<EntrancePage />}></Route>
        <Route path="/signup" element={<SignupPage />}></Route>
        <Route
          path="/main"
          element={isLogedin ? <MainPage /> : <Navigate to="/entrance" />}
        ></Route>
        <Route
          path="/product"
          element={
            isLogedin ? <ProductSearchPage /> : <Navigate to="/entrance" />
          }
        ></Route>
        <Route
          path="/place"
          element={
            isLogedin ? <PlaceSearchPage /> : <Navigate to="/entrance" />
          }
        ></Route>
        <Route path="/market/:mkCode" element={<MarketDetailPage />}></Route>
        <Route path="/store/:stCode" element={<StoreDetailPage />}></Route>
        <Route path="/basket" element={<BasketPage />}></Route>
        <Route
          path="/profile"
          element={isLogedin ? <ProfilePage /> : <Navigate to="/entrance" />}
        ></Route>
        <Route path="/history" element={<HistoryPage />}></Route>
        <Route path='/review' element={<ReviewUploadPage/>}/>
        <Route path="/stamp" element={<StampPage/>}/>
      </Routes>
    </div>
  );
};

export default App;
