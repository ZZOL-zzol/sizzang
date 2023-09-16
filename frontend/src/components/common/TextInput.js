const TextInput = (props) => {
  const onEnterKeyDown = (e) => {
    if (e.key === "Enter") {
      props.onEnterKeyDownEvent();
    }
  };

  return (
    <input
      type={props.placeholder === "PASSWORD" ? "password" : "text"}
      placeholder={props.placeholder}
      value={props.value}
      onChange={props.onChangeEvent}
      className="input w-full border-2 border-blue-100 focus:outline-blue-500"
      onKeyDown={onEnterKeyDown}
    />
  );
};

export default TextInput;
