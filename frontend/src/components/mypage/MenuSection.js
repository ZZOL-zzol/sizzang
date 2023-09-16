import React from 'react';

const MenuSection = ({ menuName, imageUrl, bgColor, onClickEvent }) => {
    return (
        <div className={`MenuSection flex justify-between ${bgColor} rounded-lg w-full h-[80px] p-2 itmes-center`} onClick={onClickEvent}>
            <div className="text-center font-bold">{menuName}</div>
            <img src={imageUrl} alt="아이콘" className="self-end rounded-full overflow-hidden w-[35px] h-[35px]" />
        </div>
    );
}

export default MenuSection;