import React from 'react';

function StoreImage({imageUrl, altText}) {
    return (
        <div className="StoreImage">
            <div className="w-[320px] h-[160px] rounded-sm overflow-hidden"> {/* 100x100 크기로 설정 */}
                <img src={imageUrl} alt={altText} className="w-full h-full object-cover" />
            </div>
            <div>점포 정보 수정</div>
        </div>
    );
}

export default StoreImage;