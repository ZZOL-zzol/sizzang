import Header from "../components/common/Header";
import Navbar from "../components/common/Navbar";
import ConsumerMyPage from "../components/mypage/consumer/ConsumerMyPage";
import SellerMyPage from "../components/mypage/seller/SellerMyPage";





const ProfilePage = ({ userType }) => {
  return (
    <div className="w-full">
      <Header title='마이페이지'/>
      <div className="w-full px-5">
      {userType === "seller" ? <SellerMyPage /> : <ConsumerMyPage />}
      </div>
      <Navbar/>
    </div>
  );
};

export default ProfilePage;
