import React from 'react';

function ProfileImage({ imageUrl, altText }) {
    return (
        <div className="w-20 h-20 rounded-full overflow-hidden"> {/* 100x100 크기로 설정 */}
          <img src={imageUrl} alt={altText} className="w-full h-full object-cover"/>
        </div>
      );
}

export default ProfileImage;