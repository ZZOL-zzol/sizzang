import React from 'react';
import PageTitle from '../../../components/common/PageTitle';
import ConsumptionCard from '../../../components/mypage/ConsumptionCard';



const ConsumptionDetailPage = () => {
    return (
        <div className="ConsumptionDetailPage  bg-whitegray font-spoqa">
            <PageTitle pageTitle="내 소비 내역" />
            <ConsumptionCard
                storeName="할머니냉면"
                marketName="청량리 경동시장"
                time="2023-09-03 오후 15:34"
                expend="18,000원"
            />
        </div>
    );
}

export default ConsumptionDetailPage;