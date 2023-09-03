import React from 'react';

function AccountSection({ accountName, accountNumber, balance, svgPath }) {
  return (
    <div className="AccountSection">
        <div className="bg-white rounded-lg shadow-lg p-4">
            <img src={svgPath} alt="계좌로고" className="w-50 h-50" />
            <p className="text-xl text-center mb-2">{accountName}</p>
            <p className="text-base text-center mb-2">{accountNumber}</p>
            <p className="text-sm text-center">{balance}</p>
            <div className="text-left">대표 계좌 변경하기 &gt;</div>
        </div>
    </div>
  );
}

export default AccountSection;