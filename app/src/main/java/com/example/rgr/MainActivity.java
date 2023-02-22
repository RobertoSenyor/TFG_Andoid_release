package com.example.rgr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Vibrator;

import com.example.rgr.JavaModule.BigList;
import com.example.rgr.JavaModule.SmallList;
import com.example.rgr.JavaModule.Types.Dot2D;
import com.example.rgr.JavaModule.Types.Int;
import com.example.rgr.JavaModule.Types.UserType;
import com.example.rgr.JavaModule.UserFactory;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private BigList BigList = new BigList();
    private int userType = 0; // поумолчанию тип Int

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button acceptIntType;
    private Button acceptPoint2DType;
    private Button closeBtn;
    private Button printListBtn;
    private Button balanceListBtn;
    private Button sortListBtn;
    private Button pushBackListBtn;
    private Button getOnPosListBtn;
    private Button insertOnPosListBtn;
    private Button removeOnPosListBtn;
    private Button changeTypeListBtn;

    public void createNewUserTypeDialog()
    {
        dialogBuilder = new AlertDialog.Builder(this);
        final View createNewUserTypePopupView = getLayoutInflater().inflate(R.layout.selectusertype_popup, null);
        acceptIntType = (Button) createNewUserTypePopupView.findViewById(R.id.selectIntPopup);
        acceptPoint2DType = (Button) createNewUserTypePopupView.findViewById(R.id.selectPoint2DPopup);
        closeBtn = (Button) createNewUserTypePopupView.findViewById(R.id.closePopup);

        dialogBuilder.setView(createNewUserTypePopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        acceptIntType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }
                userType = 0;
                generate_list(userType);
                dialog.dismiss();
            }
        });

        acceptPoint2DType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }
                userType = 1;
                generate_list(userType);
                dialog.dismiss();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void generate_list(int user_type) {

        BigList.remove_list();

        System.out.println(user_type);

        if (UserFactory.get_type_name_list().get(user_type).equals("Int")) {
            System.out.println(UserFactory.get_type_name_list().get(user_type));

            for (int j = 0; j < 3; j++) {
                BigList.push(new SmallList());

                for (int i = 0; i < 3; i++) {
                    int value = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                    BigList.push(new Int(value));
                }
            }
        } else if (UserFactory.get_type_name_list().get(user_type).equals("Dot2D")) {
            System.out.println(UserFactory.get_type_name_list().get(user_type));

            for (int j = 0; j < 3; j++) {
                BigList.push(new SmallList());

                for (int i = 0; i < 3; i++) {
                    int x = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                    int y = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                    BigList.push(new Dot2D(x, y));
                }
            }
        } else {
            System.out.println("Not available type of data");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNewUserTypeDialog();

        // ----------------------------------------------------------------------

        printListBtn = (Button) findViewById(R.id.printListBtn);

        printListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setMessage("Данные списка:\n" + BigList.print_list())
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                        .setIcon(android.R.drawable.ic_dialog_info);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("Размер списка :" + BigList.inner_count());
                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        balanceListBtn = (Button) findViewById(R.id.balanceListBtn);

        balanceListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                EditText balance_level = new EditText(a_builder.getContext());

                a_builder.setMessage("Введите размер разделения:\n")
                        .setView(balance_level)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(!balance_level.getText().toString().isEmpty()

                                )
                                {
                                    try {
                                        Integer.parseInt(balance_level.getText().toString());

                                        AlertDialog.Builder before_balance = new AlertDialog.Builder(MainActivity.this);
                                        before_balance.setMessage("Данные списка до балансировки:\n" + BigList.print_list()).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                                AlertDialog.Builder after_balance = new AlertDialog.Builder(MainActivity.this);
                                                after_balance.setMessage("Данные списка после балансировки:\n" + BigList.print_list()).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                }).setIcon(android.R.drawable.ic_dialog_info);

                                                AlertDialog after_alertDialog = after_balance.create();
                                                after_alertDialog.setTitle("Размер списка :" + BigList.inner_count());
                                                after_alertDialog.show();
                                            }
                                        }).setIcon(android.R.drawable.ic_dialog_info);

                                        AlertDialog before_alertDialog = before_balance.create();
                                        before_alertDialog.setTitle("Размер списка :" + BigList.inner_count());
                                        before_alertDialog.show();

                                        BigList.balance_list((int) Math.abs(Math.ceil(Double.parseDouble(balance_level.getText().toString()))));

                                        dialogInterface.cancel();
                                    }
                                    catch (Exception e)
                                    {

                                    }
                                }
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).setIcon(android.R.drawable.ic_menu_manage);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("Балансировка списка");

                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        sortListBtn = (Button) findViewById(R.id.sortListBtn);

        sortListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);

                a_builder.setMessage("Список до сортировки:\n" + BigList.print_list())
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                BigList.sort_list();
                                AlertDialog.Builder before_balance = new AlertDialog.Builder(MainActivity.this);
                                before_balance.setMessage("Список после сортировки:\n" + BigList.print_list())
                                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).setIcon(android.R.drawable.ic_dialog_info);

                                AlertDialog before_alertDialog = before_balance.create();
                                before_alertDialog.setTitle("Сортрованный список (битоническая сортировка)");
                                before_alertDialog.show();

                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).setIcon(android.R.drawable.ic_menu_manage);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("Сортировка списка");

                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        pushBackListBtn = (Button) findViewById(R.id.pushBackListBtn);

        pushBackListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }

                AlertDialog.Builder push_question = new AlertDialog.Builder(MainActivity.this);
                push_question.setMessage("Добавить элемент в конец списка?").setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (userType == 0)
                        {
                            int value = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                            BigList.push(new Int(value));
                        }
                        if (userType == 1)
                        {
                            int x = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                            int y = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                            BigList.push(new Dot2D(x,y));
                        }

                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                        a_builder.setMessage("Хотите ли Вы добавить еще один элемент")
                                .setNegativeButton("Нет, хватит", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                })
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        pushBackListBtn.callOnClick();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_menu_help);

                        AlertDialog alertDialog = a_builder.create();
                        alertDialog.setTitle("Добавить еще?");
                        alertDialog.show();

                        dialogInterface.cancel();
                    }
                })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_menu_help);

                AlertDialog questionAlertDialog = push_question.create();
                questionAlertDialog.setTitle("Размер списка :" + BigList.inner_count());
                questionAlertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        getOnPosListBtn = (Button) findViewById(R.id.getOnPosListBtn);

        getOnPosListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                EditText position_get = new EditText(a_builder.getContext());
                position_get.setHint("Позиция от 1 до " + BigList.inner_count());

                a_builder.setMessage("Введите позицию элемента для его просмотра:\n")
                        .setView(position_get)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        try {
                            Double.parseDouble(position_get.getText().toString());

                            if (Math.ceil(Double.parseDouble(position_get.getText().toString())) <= BigList.inner_count()
                                    &&
                                    Math.ceil(Double.parseDouble(position_get.getText().toString())) > 0
                                    &&
                                    !position_get.getText().toString().isEmpty()
                            )
                            {
                                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);

                                a_builder.setMessage("Элемент с позиции "
                                                + (int) Math.abs(Math.ceil(Double.parseDouble(position_get.getText().toString())))
                                                + ":\n"
                                                + BigList.get_item_on_position((int) Math.abs(Math.ceil(Double.parseDouble(position_get.getText().toString()))))
                                        )
                                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        }).setIcon(android.R.drawable.ic_dialog_info);

                                AlertDialog alertDialog = a_builder.create();
                                alertDialog.setTitle("Полученный элемент");
                                alertDialog.show();
                            }
                        }
                        catch (Exception e)
                        {

                        }

                        dialogInterface.cancel();
                    }
                })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("Размер списка :" + BigList.inner_count());
                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        insertOnPosListBtn = (Button) findViewById(R.id.insertOnPosListBtn);

        insertOnPosListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                EditText position_insert = new EditText(a_builder.getContext());
                position_insert.setHint("Позиция от 1 до " + BigList.inner_count());

                a_builder.setMessage("Введите позицию элемента для вставки:\n")
                        .setView(position_insert)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try{
                                    Double.parseDouble(position_insert.getText().toString());

                                    if ((int) Math.ceil(Double.parseDouble(position_insert.getText().toString())) <= BigList.inner_count()
                                            &&
                                            Math.ceil(Double.parseDouble(position_insert.getText().toString())) > 0
                                            &&
                                            !position_insert.getText().toString().isEmpty()
                                    )
                                    {
                                        if(userType == 0)
                                        {
                                            AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                                            EditText value_insert = new EditText(a_builder.getContext());
                                            value_insert.setText("777");

                                            a_builder.setMessage("Введите данные, (целочисленное число), например: 777:\n")
                                                    .setView(value_insert)
                                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                            try {
                                                                Double.parseDouble(position_insert.getText().toString());

                                                                if (!value_insert.getText().toString().isEmpty()) {
                                                                    BigList.insert_item_on_position(
                                                                            (int) Math.ceil(Double.parseDouble(position_insert.getText().toString())),
                                                                            new Int((int) Math.ceil(Double.parseDouble(value_insert.getText().toString())))
                                                                    );
                                                                }
                                                            } catch (Exception e) {

                                                            }

                                                            dialogInterface.cancel();
                                                        }
                                                    })
                                                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.cancel();
                                                        }
                                                    })
                                                    .setIcon(android.R.drawable.ic_dialog_info);

                                            AlertDialog alertDialog = a_builder.create();
                                            alertDialog.setTitle("Ввод элемента");
                                            alertDialog.show();
                                        }
                                        if(userType == 1)
                                        {
                                            AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                                            EditText value_insert = new EditText(a_builder.getContext());
                                            value_insert.setText("777;777");

                                            a_builder.setMessage("Введите данные, точка (x;y), например: 777;777:\n")
                                                    .setView(value_insert)
                                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                            String[] point2d = value_insert.getText().toString().split(";");

                                                            try {
                                                                Integer.parseInt(point2d[0]);
                                                                Integer.parseInt(point2d[1]);
                                                            }
                                                            catch (Exception e)
                                                            {

                                                            }

                                                            if(!value_insert.getText().toString().isEmpty()) {

                                                                BigList.insert_item_on_position(
                                                                        (int) Math.ceil(Double.parseDouble(position_insert.getText().toString())),
                                                                        new Dot2D(Integer.parseInt(point2d[0]), Integer.parseInt(point2d[1]))
                                                                );
                                                            }
                                                            dialogInterface.cancel();
                                                        }
                                                    })
                                                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.cancel();
                                                        }
                                                    })
                                                    .setIcon(android.R.drawable.ic_dialog_info);

                                            AlertDialog alertDialog = a_builder.create();
                                            alertDialog.setTitle("Ввод элемента");
                                            alertDialog.show();
                                        }
                                    }
                                }
                                catch (Exception e) {

                                }
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("Размер списка :" + BigList.inner_count());
                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        removeOnPosListBtn= (Button) findViewById(R.id.removeOnPosListBtn);

        removeOnPosListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                EditText position_remove = new EditText(a_builder.getContext());
                position_remove.setHint("Позиция от 1 до " + BigList.inner_count());

                a_builder.setMessage("Введите позицию элемента для его удаления:\n" + BigList.print_list())
                        .setView(position_remove)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try {
                                    Double.parseDouble(position_remove.getText().toString());

                                    if (Math.ceil(Double.parseDouble(position_remove.getText().toString())) <= BigList.inner_count()
                                            &&
                                            Math.ceil(Double.parseDouble(position_remove.getText().toString())) > 0
                                            &&
                                            !position_remove.getText().toString().isEmpty()
                                    ) {
                                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                                        a_builder.setMessage("Элемент ("
                                                        + BigList.get_item_on_position((int)Math.ceil(Double.parseDouble(position_remove.getText().toString())))
                                                        + ") под номером "
                                                        + position_remove.getText().toString()
                                                        + " был удалён!")
                                                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                })
                                                .setIcon(android.R.drawable.ic_dialog_alert);

                                        AlertDialog alertDialog = a_builder.create();
                                        alertDialog.setTitle("Удаление элемента");
                                        alertDialog.show();

                                        BigList.remove_item_on_position((int) Math.ceil(Double.parseDouble(position_remove.getText().toString())));
                                    }
                                } catch (Exception e) {

                                }
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("Размер списка :" + BigList.inner_count());
                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        changeTypeListBtn = (Button) findViewById(R.id.changeTypeListBtn);

        changeTypeListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }

                createNewUserTypeDialog();
            }
        });
    }
}