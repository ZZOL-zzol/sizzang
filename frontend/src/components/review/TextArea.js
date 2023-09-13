import React, { useEffect, useState } from 'react';



const TextArea = (props) => {


  const onTextAreaChange = (e) => {
    props.setReviewContent(e.target.value);
  };

  return (
    <textarea
      className={`bg-gray-100 ${props.height} rounded-lg w-full p-3 outline-none resize-none`}
      placeholder={props.inputPlaceholder}
      onChange={onTextAreaChange}
      value={props.ReviewContent}
    />
  );
};

export default TextArea;
