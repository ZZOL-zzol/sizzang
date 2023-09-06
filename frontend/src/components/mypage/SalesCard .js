import React from 'react';

const SalesCard  = ({ amount }) => {
    return (
        <div className="SalesCard ">
            <div className="bg-white rounded-lg shadow-lg mx-4 my-2 p-4">
                <div className="flex justify-start items-center">
                    <div>
                        <p className="text-lg font-bold">판매액 합계</p>
                    </div>
                    <p className="self-center text-xl font-bold text-right flex-1">{amount}</p>
                </div>
            </div>
        </div>
    );
}

export default SalesCard ;