import React from 'react';

const AccountCard = ({ accountName, accountNumber, balance, imageUrl }) => {
    return (
        <div className="AccountCard">
            <div className="bg-white rounded-lg shadow-lg mx-4 my-2 p-4">
                <div className="flex justify-start items-center">
                    <img src={imageUrl} alt="계좌로고" className="w-[50px] h-[50px] rounded-full overflow-hidden mr-2" />
                    <div>
                        <p className="text-lg font-bold">{accountName}</p>
                        <p className="text-sm">{accountNumber}</p>
                    </div>
                    <p className="self-center text-xl font-bold text-right flex-1">{balance}</p>
                </div>
            </div>
        </div>
    );
}

export default AccountCard;