import React from 'react';

const ConsumptionCard = ({ storeName, marketName, time, expend}) => {
    return (
        <div className="ConsumptionCard">
            <div className="bg-white rounded-lg shadow-lg mx-4 my-2 p-4">
                <div className="flex justify-start items-center">
                    <div>
                        <div className="flex justify-start">
                            <p className="text-lg font-bold mr-2">{storeName}</p>
                            <p className="text-sm">{marketName}</p>
                        </div>
                        <p className="text-left text-xs">{time}</p>
                    </div>
                    <div className="flex-1">
                        <button className="text-xs btn-primary rounded-lg p-1">리뷰쓰기</button>
                        <p className="text-sm font-bold text-right">{expend}</p>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ConsumptionCard;