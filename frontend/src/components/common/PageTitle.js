import React from 'react';

const PageTitle = ({ pageTitle }) => {
    return (
        <div className="flex items-center w-full h-[60px] p-2">
          <div className="text-left font-bold text-xl">&lt;</div>
          <div className="text-center font-bold flex-1">{pageTitle}</div>
        </div>
      );
}

export default PageTitle;