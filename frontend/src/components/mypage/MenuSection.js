import React from 'react';

const MenuSection = ({ menuName, imageUrl, bgColor, onClickEvent }) => {
    return (
        <div className={`MenuSection flex justify-between ${bgColor} rounded-lg w-full h-[80px] itmes-center px-10`} onClick={onClickEvent}>
            <div className="flex w-full items-center text-xl font-bold justify-between h-20">{menuName}<div className="flex w-[40px] justify-center">
              <img
                src={imageUrl}
                alt="회사로고"
                className="w-[40px]"
              ></img>
            </div></div>
        </div>
    );
}

export default MenuSection;