import React from 'react';

const StoreImage = ({imageUrl, altText}) => {
    return (
        <div className="StoreImage bg-white p-4">
            <div className="w-[320px] h-[160px] rounded-lg overflow-hidden m-4">
                <img src={imageUrl} alt={altText} className="w-full h-full object-cover" />
            </div>
            <div className="text-gray underline">점포 정보 수정</div>
        </div>
    );
}

export default StoreImage;