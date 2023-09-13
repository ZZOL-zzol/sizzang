import React from 'react';

const AccountSection = ({ accountName, accountNumber, balance, imageUrl }) => {
  return (
    <div className="AccountSection">
        <div className="bg-white rounded-lg shadow-lg m-4 p-4">
            <div className="flex justify-start mb-4">
              <img src={imageUrl} alt="계좌로고" className="w-[50px] h-[50px] rounded-full overflow-hidden mr-2" />
              <div>
                <p className="text-lg font-bold">{accountName}</p>
                <p className="text-sm">{accountNumber}</p>
              </div>
            </div>
            <p className="text-2xl font-bold text-center mb-4">{balance}</p>
          <div className="text-sm text-center">대표 계좌 변경하기 &gt;</div>
        </div>
    </div>
  );
}

export default AccountSection;