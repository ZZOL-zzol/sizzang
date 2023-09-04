import React from 'react';
import PageTitle from '../../../components/common/PageTitle';
import StoreImage from '../../../components/mypage/StoreImage';
import StoreInfo from '../../../components/mypage/StoreInfo';
import Button from '../../../components/common/Button';

const StoreInfoPage = () => {
    return (
        <div className="SellerMyPage bg-whitegray font-spoqa">
            <PageTitle pageTitle="점포 정보" />
            <StoreImage imageUrl="../../../../chacha2.jpg"/>
            <StoreInfo/>
            <Button btnText="정보 수정"/>
        </div>
    );
}

export default StoreInfoPage;
