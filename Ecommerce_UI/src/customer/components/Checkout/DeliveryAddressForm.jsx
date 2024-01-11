import { Button, Grid, Box, TextField } from "@mui/material";
import React from "react";
import AddressCard from "../AddressCard/AddressCard";

function DeliveryAddressForm() {

    function handleSubmit(e){
       e.preventDefault()
       const data = new FormData(e.currentTarget)
       
       const address={
        firstName:data.get("firstName"),
        lastName:data.get("lastName"),
        address:data.get("address"),
        city:data.get("city"),
        state:data.get("state"),
        zip:data.get("zip"),
        phoneNumber:data.get("phoneNumber"),
       }

       console.log(address);
    }
  return (
    <div>
      <Grid container spacing={4}>
        <Grid
          xs={12}
          lg={5}
          item
          className="border rounded-md shadow-md h-[30.5rem] overflow-scroll"
        >
          <div className="p-5 py-7 border-b cursor-pointer">
            <AddressCard />
            <Button
              sx={{ mt: 2, bgcolor: "purple" }}
              size="large"
              variant="contained"
            >
              Deliver Here
            </Button>
          </div>
        </Grid>
        <Grid item xs={12} lg={7}>
          <Box className="border rounded-md shadow-md p-5">
            <form onSubmit={handleSubmit}>
              <Grid container spacing={3}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="firstName"
                    name="firstName"
                    label="First Name"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="lastName"
                    name="lastName"
                    label="Last Name"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    id="address"
                    name="address"
                    label="Address"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                    multiline
                    rows={5}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="city"
                    name="city"
                    label="City"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="state"
                    name="state"
                    label="State/Region"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="zip"
                    name="zip"
                    label="zip / Postal code"
                    fullWidth
                    //   value={}
                    required
                    autoComplete="shipping postal-code"
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="phoneNumber"
                    name="phoneNumber"
                    label="Phone Number"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                  />
                </Grid>
                <Grid item xs={12}>
                  <Button
                    className="w-full"
                    sx={{ bgcolor: "#32CD32", color: "black" }}
                    variant="contained"
                    color="success"
                    type="submit"
                  >
                    Deliver Here
                  </Button>{" "}
                </Grid>
              </Grid>
            </form>
          </Box>
        </Grid>
      </Grid>
    </div>
  );
}

export default DeliveryAddressForm;
