const EditInput = (props) => {
    const value = props.user[props.data];
  return (
    <input
      className="bg-white rounded-lg px-2 py-1 focus:outline-myprimary w-32 text-sm"
      value={value}
      onChange={props.onInputChange}
      name={props.data}
    />
  );
};

export default EditInput;