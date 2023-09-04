import React, { useState } from 'react';
import PageTitle from '../../../components/common/PageTitle';
import ProfileSection from '../../../components/mypage/ProfileSection';
import AccountCard from '../../../components/mypage/AccountCard';
import ConsumptionDetail from './ConsumptionDetail';




const ConsumerMyPage = () => {

  // const [isDetailButtonClicked, setIsDetailButtonClicked] = useState(false);


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
      {/* 마이페이지 내에서 전환효과 주려면 라우팅으로 페이지 이동 보다는 
      같은 페이지 내에서 렌더링을 하거나 css 를 조건에 맞춰서 바꿔주는 방식이 더 쉬워서 
      이렇게 하는 게 편할거야.
      아래 ConsumptionDetail 페이지에 props로 state를 내려줘서 false일때는 화면 밖으로 밀어뒀다가
      x y 값 변경으로 왼쪽에서 오른쪽으로 슬라이드해서 들어온다거나 하면 좋을듯!*/}
      {/* <ConsumptionDetail></ConsumptionDetail> */}
    </div>
  );
}

export default ConsumerMyPage;
