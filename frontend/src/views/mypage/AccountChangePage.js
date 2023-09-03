import React from 'react';
import PageTitle from '../../components/common/PageTitle';
import AccountCard from '../../components/mypage/AccountCard';

const AccountChangePage = () => {
    return (
        <div className="SellerMyPage  bg-whitegray font-spoqa">
            <PageTitle />
            <div className="font-bold text-left text-xl ml-4 mb-6">대표계좌를 선택해주세요</div>
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
        </div>
    );
}

export default AccountChangePage;
