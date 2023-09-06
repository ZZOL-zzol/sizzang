import './App.css';
import { Navigate, Route, Routes } from 'react-router-dom';
import EntrancePage from './views/EntrancePage';
import SignupPage from './views/SignupPage';
import MainPage from './views/MainPage';
import ProfilePage from './views/ProfilePage';
import ProductSearchPage from './views/ProductSearchPage';
import MarketSearchPage from './views/MarketSearchPage';

const isLogedin = true;


const App = () => {
  return (
    <div className="App h-screen flex w-screen">
      <Routes>
        {/* 임시로 main으로 리다이렉트 */}
        {/* 버튼으로 리다이렉트시 useNavigate나 Link태그 사용 */}
        <Route path='/' element={<Navigate to='/main'/>}></Route>
        <Route path="/entrance" element={<EntrancePage/>}></Route>
        <Route path="/signup" element={<SignupPage/>}></Route>
        <Route path="/main" element={isLogedin ?<MainPage/> : <Navigate to="/entrance" />}></Route>
        <Route path="/product" element={isLogedin ?<ProductSearchPage/> : <Navigate to="/entrance" />}></Route>
        <Route path="/market" element={isLogedin ?<MarketSearchPage/> : <Navigate to="/entrance" />}></Route>
        <Route path="/profile" element={isLogedin? <ProfilePage /> : <Navigate to="/entrance" />}></Route>
      </Routes>
    </div>
  );
}

export default App;
