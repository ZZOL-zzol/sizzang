import React from 'react';
import ProfileImage from '../../../components/mypage/ProfileImage';



const SellerMyPage = () => {
  return (
    <div className="SellerMyPage">
      <h1>프로필 사진</h1>
      <ProfileImage
        imageUrl="프로필URL"
        altText="대체문구"
      />
    </div>
  );
}

export default SellerMyPage;
