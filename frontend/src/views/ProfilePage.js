import Header from "../components/common/Header";
import Navbar from "../components/common/Navbar";
import ConsumerMyPage from "../components/mypage/consumer/ConsumerMyPage";
import SellerMyPage from "../components/mypage/seller/SellerMyPage";





const ProfilePage = () => {
  const user = JSON.parse(window.localStorage.getItem('User'))

  return (
    <div className="w-full bg-background-fill">
      <Header title='마이페이지'/>
      <div className="w-full h-full">
      {user.role === "SELLER" ? <SellerMyPage user={user}/> : <ConsumerMyPage user={user}/>}
      </div>
      <Navbar/>
    </div>
  );
};

export default ProfilePage;
