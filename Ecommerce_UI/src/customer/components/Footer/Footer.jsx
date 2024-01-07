import React from "react";
import Grid from "@mui/material/Grid";
import { Button, Container, Typography } from "@mui/material";

function Footer() {
  return (
    <div>
      <Grid container className="bg-black text-white text-center mt-10 p-10">
        <Grid item xs={12} sm={6} md={3}>
          <Typography className="pb-5" variant="h1">
            LOGO
          </Typography>
          <Typography className="pb-5" variant="h4">
            Company Slogan
          </Typography>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Typography className="pb-5" variant="h6">
            Company
          </Typography>
          <div>
            <Button className="pb-5" variant="h6">
              About
            </Button>
          </div>
          <div>
            <Button className="pb-5" variant="h6">
              Contact us
            </Button>
          </div>
          <div>
            <Button className="pb-5" variant="h6">
              Our Sales
            </Button>
          </div>
          <div>
            <Button className="pb-5" variant="h6">
              Career
            </Button>
          </div>
          <div>
            <Button className="pb-5" variant="h6">
              Partners
            </Button>
          </div>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Typography className="pb-5" variant="h6">
            Solutions
          </Typography>
          <div>
            <Button className="pb-5" variant="h6">
              Marketing
            </Button>
          </div>
          <div>
            <Button className="pb-5" variant="h6">
              Analytics
            </Button>
          </div>
          <div>
            <Button className="pb-5" variant="h6">
              Insight
            </Button>
          </div>
          <div>
            <Button className="pb-5" variant="h6">
              Service
            </Button>
          </div>
          <div>
            <Button className="pb-5" variant="h6">
              Support
            </Button>
          </div>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Typography className="pb-5" variant="h6">
            Documentation
          </Typography>
          <div>
            <Button className="pb-5" variant="h6">
              Guid
            </Button>
          </div>
          <div>
            <Button className="pb-5" variant="h6">
              API status
            </Button>
          </div>
        </Grid>
        <Grid item xs={12} className="pt-20">
          <Typography className="pb-5" variant="body2" component="p">
            © 2024 My Company. All rights reserved
          </Typography>
          <Typography align="center" className="pb-5" variant="body2" component="p">
            Made in Bharath
          </Typography>
          <Typography align="center" className="pb-1" variant="body2" component="p">
            Made by Tex
          </Typography>
        </Grid>
      </Grid>
    </div>
  );
}

export default Footer;
