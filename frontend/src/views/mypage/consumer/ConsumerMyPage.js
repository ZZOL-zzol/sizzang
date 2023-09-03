import React from 'react';
import PageTitle from '../../../components/common/PageTitle';
import ProfileSection from '../../../components/mypage/ProfileSection';
import AccountCard from '../../../components/mypage/AccountCard';


const ConsumerMyPage = () => {
  return (
    <div className="ConsumerMyPage  bg-whitegray font-spoqa">
      <PageTitle pageTitle="마이페이지" />
      <ProfileSection />
      <AccountCard
        imageUrl="../chacha2.jpg"
        accountName="차곡차곡계좌"
        accountNumber="121254-4564"
        balance="420원"
      />
      <AccountCard
        imageUrl="../chacha2.jpg"
        accountName="차곡차곡계좌"
        accountNumber="121254-4564"
        balance="420원"
      />
      <div className="flex justify-center text-left text-sm text-gray mt-4">
        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
          <path d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zM232 344V280H168c-13.3 0-24-10.7-24-24s10.7-24 24-24h64V168c0-13.3 10.7-24 24-24s24 10.7 24 24v64h64c13.3 0 24 10.7 24 24s-10.7 24-24 24H280v64c0 13.3-10.7 24-24 24s-24-10.7-24-24z" />
        </svg>
        계좌 간편 등록
      </div>
    </div>
  );
}

export default ConsumerMyPage;
