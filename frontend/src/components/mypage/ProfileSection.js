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
const SellerInfo = ({ marketName, userName, setOpenProfileEdit }) => {
  return (
    <div>
      <div className="text-left text-lg font-bold">{marketName}</div>
      <div className="text-left text-xl font-bold">{userName}</div>
      <div className="text-left text-sm text-outline" onClick={()=>setOpenProfileEdit(true)}>내 정보 수정 &gt;</div>
    </div>
  );
}

const ConsumerInfo = (props) => {
  return (
    <div>
      
      <div className="text-left text-xl font-bold">{props.userName}님</div>
      <div className="text-left text-lg font-bold">안녕하세요!</div>
      <div className="text-left text-sm text-outline" onClick={()=>props.setOpenProfileEdit(true)}>내 정보 보기 &gt;</div>
    </div>
  );
}


const ProfileSection = (props) => {
  return (
    <div className="w-full">
      <div className="flex items-center gap-3">
        <ProfileImage       
          imageUrl="../chacha2.jpg"
          altText="대체문구"
        />
        {props.user.role === 'CUSTOMER'?<ConsumerInfo 
          userName={props.user.userName}
          setOpenProfileEdit = {props.setOpenProfileEdit}
        />:<SellerInfo 
        marketName='뿡빵뿡시장'
        userName={props.user.userName}
        setOpenProfileEdit = {props.setOpenProfileEdit}
      />}
        
      </div>
    </div>
  );
}

export default ProfileSection;