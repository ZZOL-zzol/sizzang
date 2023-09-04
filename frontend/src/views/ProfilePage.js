import ConsumerMyPage from "../components/mypage/consumer/ConsumerMyPage";
import SellerMyPage from "../components/mypage/seller/SellerMyPage";

const ProfilePage = ({ userType }) => {
  return (
    <div>
      {userType === "seller" ? <SellerMyPage /> : <ConsumerMyPage />}
    </div>
  );
};

export default ProfilePage;
