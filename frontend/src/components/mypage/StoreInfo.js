import React from 'react';

function StoreInfo() {
    return (
        <div className="StoreInfo bg-white p-4">
            {/* 조건문 걸어서 시장/ 점포 정보 구분 */}
            <div className="text-left font-bold">시장정보</div>
            <div className="text-left font-bold">점포정보</div>
        </div>
    );
}

export default StoreInfo;