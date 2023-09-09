import React from 'react';


/* 프로필 사진 */
const ProfileImage = ({ imageUrl, altText }) => {
  return (
    <div className="w-20 h-20 rounded-full overflow-hidden"> {/* 100x100 크기로 설정 */}
      <img src={imageUrl} alt={altText} className="w-full h-full object-cover" />
    </div>
  );
}


/* 유저 정보 */
const UserInfo = ({ marketName, userName }) => {
  return (
    <div className="bg-gray-800 text-black p-4">
      <div className="text-left font-bold">{marketName}</div>
      <div className="text-left font-bold">{userName}</div>
      <div className="text-left text-sm text-gray">내 정보 수정 &gt;</div>
    </div>
  );
}


const ProfileSection = () => {
  return (
    <div className="ProfileSection">
      <div className="flex items-center m-4">
        <ProfileImage       
          imageUrl="../chacha2.jpg"
          altText="대체문구"
        />
        <UserInfo 
          marketName="경동시장"
          userName="할머니냉면"
        />
      </div>
    </div>
  );
}

export default ProfileSection;