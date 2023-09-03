import React from 'react';
import PageTitle from '../../../components/common/PageTitle';
import ProfileSection from '../../../components/mypage/ProfileSection';
import AccountSection from '../../../components/mypage/AccountSection';
import MenuSection from '../../../components/mypage/MenuSection';


const SellerMyPage = () => {
    return (
        <div className="SellerMyPage">
            <PageTitle pageTitle="페이지제목" />
            <ProfileSection />
            <AccountSection
              accountName="차곡차곡계좌"
              accountNumber="121254-4564"
              balance="420원"
            />
            <MenuSection
              menuName="상품관리"
            />
        </div>
    );
}

export default SellerMyPage;
